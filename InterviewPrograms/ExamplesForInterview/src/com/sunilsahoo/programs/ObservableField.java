package com.sunilsahoo.programs;

public class ObservableField<T>{
    static final long serialVersionUID = 1L;
    private T mValue;

    /**
     * Wraps the given object and creates an observable object
     *
     * @param value The value to be wrapped as an observable.
     */
    public ObservableField(T value) {
        mValue = value;
    }

    /**
     * Creates an empty observable object
     */
    public ObservableField() {
    }

    /**
     * @return the stored value.
     */
    public T get() {
        return mValue;
    }

    /**
     * Set the stored value.
     */
    public void set(T value) {
        if (value != mValue) {
            mValue = value;
        }
    }
}
