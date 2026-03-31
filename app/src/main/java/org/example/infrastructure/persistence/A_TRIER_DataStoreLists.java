package org.example.infrastructure.persistence;

import java.util.concurrent.atomic.AtomicLong;

public class A_TRIER_DataStoreLists {
        
        private AtomicLong userIdSeq = new AtomicLong(1);
        private AtomicLong postIdSeq = new AtomicLong(1);
        private AtomicLong commentIdSeq = new AtomicLong(1);

        public A_TRIER_DataStoreLists() {}
}
