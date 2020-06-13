package com.zoe;

import com.zoe.client.Client;
import org.apache.commons.cli.*;
import com.zoe.server.Server;

/**
 * @author zy
 */
public class ApplicationStart {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        int port = 3456;
        Options options = new Options();
        options.addOption("h", true, "listen port");
        options.addOption("s", false, "start com.zoe.server");
        options.addOption("p", true, "com.zoe.server listen port");
        options.addOption("c", true, "start com.zoe.client to specific com.zoe.server");

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cmd.hasOption('h')) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Options", options);
        } else {
            if (cmd.hasOption('s')) {
                if (cmd.hasOption('p')) {
                    port = Integer.parseInt(cmd.getOptionValue('p'));
                }
                new Server().start(port);
            }
            if (cmd.hasOption('c')) {
                var host = cmd.getOptionValue('c');
                port = Integer.parseInt(cmd.getOptionValue('p'));
                new Client().start(host, port);
            }
        }
    }
}
