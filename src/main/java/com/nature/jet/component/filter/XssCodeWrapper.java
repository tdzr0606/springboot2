package com.nature.jet.component.filter;


import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * km_leader
 * XssCodeFilter
 *
 * @author:竺志伟
 * @date :2018-01-09 17:28:09
 * @Author: 竺志伟
 * @Date: 2018 -01-09 15:10
 */
public class XssCodeWrapper extends HttpServletRequestWrapper
{
    boolean isUpData = false;//判断是否是上传 上传忽略

    public XssCodeWrapper(HttpServletRequest request)
    {
        super(request);
        String contentType = request.getContentType();
        if(null != contentType)
        {
            isUpData = contentType.startsWith("multipart");
        }
    }

    @Override
    public String[] getParameterValues(String parameter)
    {
        String[] values = super.getParameterValues(parameter);
        if(values == null)
        {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for(int i = 0; i < count; i++)
        {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter)
    {
        String value = super.getParameter(parameter);
        if(value == null)
        {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name)
    {
        String value = super.getHeader(name);
        if(value == null)
            return null;
        return cleanXSS(value);
    }

    private static String cleanXSS(String value)
    {
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        if(isUpData)
        {
            return super.getInputStream();
        }
        else
        {

            final ByteArrayInputStream bais =
                    new ByteArrayInputStream(inputHandlers(super.getInputStream()).getBytes());

            return new ServletInputStream()
            {

                @Override
                public int read() throws IOException
                {
                    return bais.read();
                }

                @Override
                public boolean isFinished()
                {
                    return false;
                }

                @Override
                public boolean isReady()
                {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener)
                {

                }
            };
        }
    }

    public String inputHandlers(ServletInputStream servletInputStream)
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(servletInputStream, Charset.forName("UTF-8")));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(servletInputStream != null)
            {
                try
                {
                    servletInputStream.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return cleanXSS(sb.toString());
    }

}
