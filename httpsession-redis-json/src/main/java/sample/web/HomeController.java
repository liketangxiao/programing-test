/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jitendra on 5/3/16.
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/setValue")
	public String setValue(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "value", required = false) String value,
			HttpServletRequest request) {
		if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
			request.getSession().setAttribute(key, value);
		}
		return "home";
	}
}
