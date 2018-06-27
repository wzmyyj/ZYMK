package top.wzmyyj.wzm_sdk.inter;

/**
 * Created by wzm on 2018/4/29 0029.
 */

public abstract class SingleIVD<T> implements IVD<T> {
    @Override
    public boolean isForViewType(T item, int position) {
        return true;
    }
}
