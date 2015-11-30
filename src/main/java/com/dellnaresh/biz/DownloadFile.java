package com.dellnaresh.biz;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by nareshm on 30/11/2015.
 */
public interface DownloadFile {
    public void download(String fileUrl) throws IOException;
}
