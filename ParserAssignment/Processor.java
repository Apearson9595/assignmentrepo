package uk.ac.uos.i2p.week6;

import java.io.IOException;
import java.util.Iterator;

public interface Processor {
	void process(Iterator<String> it) throws IOException;

}
