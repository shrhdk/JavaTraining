package jpl.chapter1.exercise16;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.sun.xml.internal.rngom.parse.compact.EOFException;

public class MyUtilities {
    public double[] getDataSet(String setName) throws BadDataSetException {
	String file = setName + ".dset";
	FileInputStream in = null;
	try {
	    in = new FileInputStream(file);
	    return readDataSet(in);
	} catch (IOException e) {
	    throw new BadDataSetException(setName, e);
	} finally {
	    try {
		if (in != null)
		    in.close();
	    } catch (IOException e) {
		; // 無視: データの読み込みは成功しているか、あるいは、BadDataSetExceptionをスローしようとしている。
	    }
	}
    }

    private double[] readDataSet(FileInputStream in) throws IOException {
	DataInputStream din = new DataInputStream(in);
	double[] dataSet = new double[din.available()];

	try {
	    for (int i = 0; i < dataSet.length; i++)
		dataSet[i] = din.readDouble();
	} catch (EOFException e) {
	    throw e;
	} catch (IOException e) {
	    throw e;
	} finally {
	    try {
		if (din != null)
		    din.close();
	    } catch (IOException e) {
		// Ignore
		e.printStackTrace();
	    }
	}

	return dataSet;
    }
}
