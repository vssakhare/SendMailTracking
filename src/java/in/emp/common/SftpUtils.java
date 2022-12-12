/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;


/**
 *
 * @author acer
 */

import java.util.Collection;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import com.jcraft.jsch.SftpException;
import java.io.File;
import com.jcraft.jsch.Channel;
import java.util.Properties;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSch;
import java.util.Vector;

public class SftpUtils
{
    private static final int PORT = 22;
    private final String user;
    private final String host;
    private final String privateKey;
    private final byte[] keyPassword;
    private final String password;
    private JSch jsch;
    private Session session;
    private ChannelSftp sftp;
    private String[] ignoreFiles;
    
    public SftpUtils(final String user, final String host, final String password) 
    {
        System.out.println("Inside B80_Sftp");
        this.user = user;
        this.host = host;
        this.privateKey = "";
        this.keyPassword = null;
        this.password = password;
        try {
            this.init();
        }
        catch (JSchException e) {
            throw new RuntimeException("Could not connect to host [" + host + "] for User [" + user + "]", (Throwable)e);
        }
    }
    
    public String[] getIgnoreFiles() {
        return this.ignoreFiles;
    }
    
    public void setIgnoreFiles(final String[] ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }
    
    public ChannelSftp getSftp() {
        return this.sftp;
    }
    
    private void init() throws JSchException {
        this.jsch = new JSch();
        (this.session = this.jsch.getSession(this.user, this.host, 22)).setPassword(this.password);
        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        this.session.setConfig(config);
        this.session.connect();
        final Channel channel = this.session.openChannel("sftp");
        channel.connect();
        this.sftp = (ChannelSftp)channel;
    }
    
    public void downloadFiles(final String remoteFolder,final String filename, final String localFolder, boolean deleteOriginalFileFlagYN) throws SftpException {
        final File localFile = new File(localFolder);
        if (localFile.exists()) {
            final Vector<ChannelSftp.LsEntry> fileList = (Vector<ChannelSftp.LsEntry>)this.sftp.ls(remoteFolder);
            for (final ChannelSftp.LsEntry file : fileList) {
                if (isRealFile(file.getFilename()) && !this.ignoreFile(file.getFilename()) && !file.getAttrs().isDir()) {
                    final File destFile = new File(localFolder, file.getFilename());
                   
                    System.out.println("Remote Folder:" + remoteFolder + ":File:" + file.getFilename() + ":LocalFolder:" + localFolder);
                    try {
                        this.sftp.get(remoteFolder + filename, localFolder);
                        System.out.println("download " + filename);
                        if(deleteOriginalFileFlagYN)
                            destFile.delete();
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
        else {
        }
    }
    
    private boolean ignoreFile(final String fileName) {
        if (this.ignoreFiles != null) {
            for (final String ignore : this.ignoreFiles) {
                if (fileName.startsWith(ignore) || fileName.contains(ignore)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void uploadFiles(final String localFolder, final String remoteFolder, boolean deleteOriginalFileFlagYN) throws SftpException {
        final File localFile = new File(localFolder);
        if (localFile.exists()) {
            final Collection<File> fileList = (Collection<File>)FileUtils.listFiles(localFile, TrueFileFilter.TRUE, (IOFileFilter)null);
            try {
                this.sftp.mkdir(remoteFolder);
            }
            catch (Exception ex) {}
            for (final File file : fileList) {
                if (isRealFile(file.getName()) && !this.ignoreFile(file.getName())) {
                    System.out.println("fileName:" + file.getAbsolutePath() + ":" + file.getName());
                    try {
                        this.sftp.put(file.getAbsolutePath(), remoteFolder);
                        if(deleteOriginalFileFlagYN)
                            file.delete();
                    }
                    catch (Exception e) {
                    }
                }
            }
        }
        else {
        }
    }
    
    public void destroy() {
        this.sftp.exit();
        this.sftp.disconnect();
        this.session.disconnect();
    }
    
    public static boolean isRealFile(final String filename) {
        return !filename.equals("..") && !filename.equals(".");
    }
    
    static {
    }

}

