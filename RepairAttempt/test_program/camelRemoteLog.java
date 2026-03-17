package org.firstinspires.ftc.teamcode.camel;
import android.annotation.SuppressLint;

import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class camelRemoteLog extends Thread {
    String remote_ip;
    private boolean running = false;
    private boolean software_enabled = true;
    private DatagramSocket socket;
    private StringBuilder buf;
    private boolean stopRq = false;
    private long last_sent_packet, start_time;
    int send_errors=0;
    private boolean timestamp=true;
    int total_bytes_sent=0;

    public camelRemoteLog(String remote_ip) {
        this.remote_ip = remote_ip;
        last_sent_packet = System.currentTimeMillis();
        start_time = System.currentTimeMillis();
        try {
            buf = new StringBuilder();
            socket = new DatagramSocket();
            running = true;
        } catch (Exception e) {
            running = false;
        }
    }

    public void update() {
        if (!running || !software_enabled) return;
        InetAddress address;
        try {
            address = InetAddress.getByName(remote_ip);
            DatagramPacket packet = new DatagramPacket(buf.toString().getBytes(StandardCharsets.UTF_8), buf.length(), address, 59870);
            socket.send(packet);
            last_sent_packet = System.currentTimeMillis();
            send_errors=0;
            total_bytes_sent+=buf.length();
            buf.setLength(0);
        } catch (Exception e) {
            send_errors++;
            if(send_errors>10) running = false;
        }
    }

    public void run() {
        while (!stopRq) {
            try {
                this.update();
            } catch (Exception ignored) {
                ;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
                ;
            }
        }
        this.printf("Rlog terminated\n");
        this.update();
        socket.close();
    }

    public void finish() {
        stopRq = true;
        this.printf("Rlog requested to stop...\n");
    }

    @SuppressLint("DefaultLocale")
    public void printf(String format, Object... args) {
        if (!running || !software_enabled) return;
        try {
            if(timestamp) {
                long ms = System.currentTimeMillis() - start_time;
                long s = ms / 1000;
                ms %= 1000;
                buf.append(String.format("%3d.%03d ", s, ms));
            }
            buf.append(String.format(format, args));
        } catch (Exception e) {
            buf.append("Exception in camelRemoteLog.printf(");
            buf.append(format);
            buf.append("): ");
            buf.append(e.toString());
            buf.append("\n");
        }
    }

    public void print(String format, Object... args) {
        boolean old_ts_value=timestamp;
        timestamp=false;
        printf(format,args);
        timestamp=old_ts_value;
    }


        @SuppressLint("DefaultLocale")
    public String getStatus() {
        String e = "";
        if (running) e += "run";
        else e += "stop";
        e += ", sw ";
        if (software_enabled) e += "en";
        else e += "dis";
        e += ", ";
        e += remote_ip;
        //e += ":59870";
        e += String.format(" time %d", System.currentTimeMillis() - last_sent_packet);
        e += String.format(" err:%d", send_errors);
        e += String.format(" b:%d", total_bytes_sent);
        return e;
    }

    public void enable(boolean new_state) {
        software_enabled = new_state;
    }
    public void timestamp(boolean new_state) {timestamp = new_state;}
}



