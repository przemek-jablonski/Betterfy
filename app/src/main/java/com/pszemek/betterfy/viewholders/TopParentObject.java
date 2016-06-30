package com.pszemek.betterfy.viewholders;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopParentObject implements ParentObject {

    public String parentText;
    private List<Object> childrenList;

    public TopParentObject() {
    }

    public TopParentObject(String parentText) {
        this.parentText = parentText;
    }

    @Override
    public List<Object> getChildObjectList() {
        return childrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        if (childrenList == null) {
            childrenList = list;
        } else {
            childrenList.clear();
            childrenList.addAll(list);
        }
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }
}
