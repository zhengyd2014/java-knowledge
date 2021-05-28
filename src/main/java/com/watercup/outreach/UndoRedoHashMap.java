package com.watercup.outreach;

import java.util.HashMap;

import java.util.Stack;

public class UndoRedoHashMap<K, V> extends HashMap<K, V> {

    public final static String OP_INSERT = "insert";
    public final static String OP_UPDATE = "update";
    public final static String OP_DELETE = "delete";


    Stack<ChangeLog<K, V>> changeLogs = new Stack<>();
    @Override
    public V put(K key, V value) {
        V oldv= get(key);
        super.put(key, value);
        String op = null;
        if (oldv == null) op = OP_INSERT;
        else op = OP_UPDATE;
        changeLogs.push(new ChangeLog(op, key, oldv, value));
        return oldv;
    }

    public void undo() {
        if (changeLogs.isEmpty()) return;

        ChangeLog previousOp = changeLogs.pop();
        if (previousOp.op.equals(OP_INSERT)) {
            super.remove(previousOp.key);
        } else if (previousOp.op.equals(OP_DELETE)) {
            super.put((K)previousOp.key, (V)previousOp.oldValue);
        }
    }

}

class ChangeLog<K, V> {
    String op;
    K key;
    V oldValue;
    V newValue;

    public ChangeLog(String op, K key, V oldValue, V newValue) {
        this.op = op;
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
