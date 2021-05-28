package com.cloudkitchens.ingestor;

import com.cloudkitchens.datamodel.Order;

public interface OrderIngestor {

    public Order poll();
}
