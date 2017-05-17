package com.bytetrend.sandbox.java.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {
    VerifiableProperties props;
    public SimplePartitioner(VerifiableProperties props){
        this.props = props;
    }
    @Override
    public int partition(Object key, int numPartitions) {
        int partition = 0;
        int iKey = Integer.parseInt(key.toString());
        if (iKey > 0) {
            partition = iKey % numPartitions;
        }
        return partition;

    }
}