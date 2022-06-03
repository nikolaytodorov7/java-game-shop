package ogs.dao.impl;

import ogs.dao.IdGenerator;

public class IdGeneratorImpl implements IdGenerator<Long> {
    private long lastId = 0;

    public IdGeneratorImpl() {
    }

    public IdGeneratorImpl(long initialValue) {
        this.lastId = initialValue;
    }

    @Override
    public Long getNextId() {
        return ++lastId;
    }

    @Override
    public Long getCurrentId() {
        return lastId;
    }

    @Override
    public void reset(Long newInitialValue) {
        lastId = newInitialValue;
    }
}
