/**
 * Copyright (C) 2010-2013 Alibaba Group Holding Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ysepay.test.rocketmq;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import com.alibaba.rocketmq.common.message.MessageQueue;

/**
 * 根据消费端编号，分配指定的队列算法
 * 
 * @author fuchong<yubao.fyb@alibaba-inc.com>
 * @author manhong.yqd<manhong.yqd@taobao.com>
 * @since 2013-7-24
 */
public class FiexedAllocateMessageQueueStrategy
		implements AllocateMessageQueueStrategy {
	@Override
	public List<MessageQueue> allocate(String currentCID,
			List<MessageQueue> mqAll, List<String> cidAll) {
		if (currentCID == null || currentCID.length() < 1) {
			throw new IllegalArgumentException("currentCID is empty");
		}
		if (mqAll == null || mqAll.size() < 1) {
			throw new IllegalArgumentException(
					"mqAll is null or mqAll size < 1");
		}
		if (cidAll == null || cidAll.size() < 1) {
			throw new IllegalArgumentException(
					"cidAll is null or cidAll size < 1");
		}

		List<MessageQueue> result = new ArrayList<MessageQueue>();
		if (!cidAll.contains(currentCID)) { // 不存在此ConsumerId ,直接返回
			return result;
		}

		int consumerClientId = Integer.parseInt(
				currentCID.substring(currentCID.lastIndexOf('_') + 1));
		for (int i = consumerClientId * 3; i < consumerClientId * 3 + 3; i++) {
			result.add(mqAll.get(i));
		}
		return result;
	}
}
