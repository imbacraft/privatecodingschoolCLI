/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class CourseStream {
    private int streamId;
    private String StreamName;

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getStreamName() {
        return StreamName;
    }

    public void setStreamName(String StreamName) {
        this.StreamName = StreamName;
    }

    public CourseStream(int streamId, String StreamName) {
        this.streamId = streamId;
        this.StreamName = StreamName;
    }

    public CourseStream() {
    }

    @Override
    public String toString() {
        return "CourseStream{" + "streamId=" + streamId + ", StreamName=" + StreamName + '}';
    }
            
}
