package com.company.p04;

import java.io.IOException;
import java.net.MalformedURLException;

public interface Http {
    String get(String targetUrl) throws IOException;
}
