package com.hxr.designmodel.processlinkmodel;

/**
 * 责任链设计模式
 * 前一个方法成功结果，给后一个方法使用，
 * 下一个对象处理完转交给下一个对象，一次类推
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public abstract class AbstractProcessingLinkModel<T> {

    protected AbstractProcessingLinkModel<T> successor;

    public void setSuccessor(AbstractProcessingLinkModel<T> successor) {
        this.successor = successor;
    }

    /**
     * 责任链具体处理
     *
     * @param input 入参
     * @return T
     */
    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    protected abstract T handleWork(T input);
}
