/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;

/**
 *
 * @author Pooja Jadhav
 */
import in.emp.vendor.bean.PoStatusBean;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Partition<PoStatusBean> extends AbstractList<List<PoStatusBean>> {

    private final List<PoStatusBean> list;
    private final int chunkSize;

    public Partition(List<PoStatusBean> list, int chunkSize) {
        this.list = new ArrayList<PoStatusBean>(list);
        this.chunkSize = chunkSize;
    }

    public static <PoStatusBean> Partition<PoStatusBean> ofSize(List<PoStatusBean> list, int chunkSize) {
        return new Partition<PoStatusBean>(list, chunkSize);
    }

    @Override
    public List<PoStatusBean> get(int index) {
        int start = index * chunkSize;
        int end = Math.min(start + chunkSize, list.size());

        if (start > end) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of the list range <0," + (size() - 1) + ">");
        }

        return new ArrayList<PoStatusBean>(list.subList(start, end));
    }

    @Override
    public int size() {
        return (int) Math.ceil((double) list.size() / (double) chunkSize);
    }
}
