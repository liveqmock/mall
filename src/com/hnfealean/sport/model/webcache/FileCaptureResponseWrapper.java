package com.hnfealean.sport.model.webcache;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.opensymphony.oscache.web.filter.SplitServletOutputStream;

public class FileCaptureResponseWrapper extends HttpServletResponseWrapper {

	public FileCaptureResponseWrapper(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}
//	private ResponseContent result = null;
//    private boolean fragment = false;
//    private int status = SC_OK;
//    private long cacheControl = -60;
//    private PrintWriter cachedWriter = null;
//	public FileCaptureResponseWrapper(HttpServletResponse response) {
//		super(response);
//		result = new ResponseContent();
//	}
//	public ResponseContent getContent() {
//        // Flush the buffer
//        try {
//            flush();
//        } catch (IOException ignore) {
//        }
//        
//        // Create the byte array
//        result.commit();
//
//        // Return the result from this response
//        return result;
//    }
//    /**
//     * Set the content type
//     *
//     * @param value The content type
//     */
//    public void setContentType(String value) {
//       
//
//        super.setContentType(value);
//        result.setContentType(value);
//    }
//
//    /**
//     * Set the date of a header
//     *
//     * @param name The header name
//     * @param value The date
//     */
//    public void setDateHeader(String name, long value) {
//    	  result.setLastModified(value);
//    	  result.setExpires(value);
// 
//        super.setDateHeader(name, value);
//    }
//
//    /**
//     * Add the date of a header
//     *
//     * @param name The header name
//     * @param value The date
//     */
//    public void addDateHeader(String name, long value) {
//    	 result.setLastModified(value);
//    	 result.setExpires(value);
//
//        super.addDateHeader(name, value);
//    }
//
//    /**
//     * Set a header field
//     *
//     * @param name The header name
//     * @param value The header value
//     */
//    public void setHeader(String name, String value) {
//    	  result.setContentType(value);
//    	  result.setContentEncoding(value);
//
//        super.setHeader(name, value);
//    }
//
//    /**
//     * Add a header field
//     *
//     * @param name The header name
//     * @param value The header value
//     */
//    public void addHeader(String name, String value) {
//        
//    	  result.setContentType(value);
//    	  result.setContentEncoding(value);
//
//        super.addHeader(name, value);
//    }
//
//    /**
//     * Set the int value of the header
//     *
//     * @param name The header name
//     * @param value The int value
//     */
//    public void setIntHeader(String name, int value) {
//        super.setIntHeader(name, value);
//    }
//
//    /**
//     * We override this so we can catch the response status. Only
//     * responses with a status of 200 (<code>SC_OK</code>) will
//     * be cached.
//     */
//    public void setStatus(int status) {
//        super.setStatus(status);
//        this.status = status;
//    }
//
//    /**
//     * We override this so we can catch the response status. Only
//     * responses with a status of 200 (<code>SC_OK</code>) will
//     * be cached.
//     */
//    public void sendError(int status, String string) throws IOException {
//        super.sendError(status, string);
//        this.status = status;
//    }
//
//    /**
//     * We override this so we can catch the response status. Only
//     * responses with a status of 200 (<code>SC_OK</code>) will
//     * be cached.
//     */
//    public void sendError(int status) throws IOException {
//        super.sendError(status);
//        this.status = status;
//    }
//
//    /**
//     * We override this so we can catch the response status. Only
//     * responses with a status of 200 (<code>SC_OK</code>) will
//     * be cached.
//     */
//    public void setStatus(int status, String string) {
//        super.setStatus(status, string);
//        this.status = status;
//    }
//
//    /**
//     * We override this so we can catch the response status. Only
//     * responses with a status of 200 (<code>SC_OK</code>) will
//     * be cached.
//     */
//    public void sendRedirect(String location) throws IOException {
//        this.status = SC_MOVED_TEMPORARILY;
//        super.sendRedirect(location);
//    }
//
//    /**
//     * Retrieves the captured HttpResponse status.
//     */
//    public int getStatus() {
//        return status;
//    }
//
//    /**
//     * Set the locale
//     *
//     * @param value The locale
//     */
//    public void setLocale(Locale value) {
//        super.setLocale(value);
//        result.setLocale(value);
//    }
//
//    /**
//     * Get an output stream
//     *
//     * @throws IOException
//     */
//    public ServletOutputStream getOutputStream() throws IOException {
//        // Pass this faked servlet output stream that captures what is sent
//
//
//        return new SplitServletOutputStream(result.getOutputStream(), super.getOutputStream());
//    }
//
//    /**
//     * Get a print writer
//     *
//     * @throws IOException
//     */
//    public PrintWriter getWriter() throws IOException {
//        if (cachedWriter == null) {
//            String encoding = getCharacterEncoding();
//            if (encoding != null) {
//                cachedWriter = new PrintWriter(new OutputStreamWriter(getOutputStream(), encoding));
//            } else { // using the default character encoding
//                cachedWriter = new PrintWriter(new OutputStreamWriter(getOutputStream()));
//            }
//        }
//
//        return cachedWriter;
//    }
//    
//    /**
//     * Flushes all streams.
//     * @throws IOException
//     */
//    private void flush() throws IOException {
//        
//
//        if (cachedWriter != null) {
//            cachedWriter.flush();
//        }
//    }
//
//    public void flushBuffer() throws IOException {
//        super.flushBuffer();
//        flush();
//    }
//
//    /**
//     * Returns a boolean indicating if the response has been committed. 
//     * A commited response has already had its status code and headers written.
//     * 
//     * @see javax.servlet.ServletResponseWrapper#isCommitted()
//     */
//    public boolean isCommitted() {
//        return super.isCommitted(); // || (result.getOutputStream() == null);
//    }
//
//    /**
//     * Clears any data that exists in the buffer as well as the status code and headers.
//     * If the response has been committed, this method throws an IllegalStateException.
//     * @see javax.servlet.ServletResponseWrapper#reset()
//     */
//    public void reset() {
//       
//        super.reset();
//        /*
//        cachedWriter = null;
//        result = new ResponseContent();
//        cacheOut = null;
//        fragment = false;
//        status = SC_OK;
//        expires = CacheFilter.EXPIRES_ON;
//        lastModified = CacheFilter.LAST_MODIFIED_INITIAL;
//        cacheControl = -60;
//        // time ?
//        */
//    }
//
//    /**
//     * Clears the content of the underlying buffer in the response without clearing headers or status code. 
//     * If the response has been committed, this method throws an IllegalStateException.
//     * @see javax.servlet.ServletResponseWrapper#resetBuffer()
//     */
//    public void resetBuffer() {
//       // log.info("CacheHttpServletResponseWrapper:resetBuffer()");
//        super.resetBuffer();
//        /*
//        //cachedWriter = null;
//        result = new ResponseContent();
//        //cacheOut = null;
//        //fragment = false;
//        */
//    }


}