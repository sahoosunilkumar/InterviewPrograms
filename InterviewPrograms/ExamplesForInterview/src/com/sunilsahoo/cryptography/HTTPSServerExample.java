package com.sunilsahoo.cryptography;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;

//
// Example of an HTTPS server to illustrate SSL certificate and socket
public class HTTPSServerExample {
	public static void main(String[] args) throws IOException {
		//
		// create an SSL socket using the factory and pick port 8080
		SSLServerSocketFactory sslsf = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		ServerSocket ss = sslsf.createServerSocket(8080);
		//
		// loop forever
		while (true) {
			try {
				//
				// block waiting for client connection
				Socket s = ss.accept();
				System.out.println("Client connection made");
				// get client request
				BufferedReader in = new BufferedReader(
						new InputStreamReader(s.getInputStream()));
				System.out.println(in.readLine());
				//
				// make an HTML response
				PrintWriter out = new PrintWriter(s.getOutputStream());
				out.println("<HTML><HEAD><TITLE>HTTPS Server Example</TITLE>"
						+ "</HEAD><BODY><H1>Hello World!</H1></BODY></HTML>\n");
				//
				// Close the stream and socket
				out.close();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
