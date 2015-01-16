package org.zero.base.test;

/**
 * 64λID (42(����)+5(����ID)+5(ҵ�����)+12(�ظ��ۼ�))
 * 
 * @author Polim
 */
public class IdWorker {
	private final static long twepoch = 1288834974657L;
	// ������ʶλ��
	private final static long workerIdBits = 5L;
	// �������ı�ʶλ��
	private final static long datacenterIdBits = 5L;
	// ����ID���ֵ
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// ��������ID���ֵ
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	// ����������λ
	private final static long sequenceBits = 12L;
	// ����IDƫ����12λ
	private final static long workerIdShift = sequenceBits;
	// ��������ID����17λ
	private final static long datacenterIdShift = sequenceBits + workerIdBits;
	// ʱ���������22λ
	private final static long timestampLeftShift = sequenceBits + workerIdBits
			+ datacenterIdBits;

	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

	private static long lastTimestamp = -1L;

	private long sequence = 0L;
	private final long workerId;
	private final long datacenterId;

	public IdWorker(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					"worker Id can't be greater than %d or less than 0");
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					"datacenter Id can't be greater than %d or less than 0");
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception(
						"Clock moved backwards.  Refusing to generate id for "
								+ (lastTimestamp - timestamp) + " milliseconds");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (lastTimestamp == timestamp) {
			// ��ǰ�����ڣ���+1
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				// ��ǰ�����ڼ������ˣ���ȴ���һ��
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		// IDƫ������������յ�ID��������ID
		long nextId = ((timestamp - twepoch) << timestampLeftShift)
				| (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;

		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
}
