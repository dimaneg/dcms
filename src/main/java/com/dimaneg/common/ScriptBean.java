package com.dimaneg.common;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.extensions.event.CompleteEvent;
import org.slf4j.Logger;

import com.dimaneg.common.api.Log;

@Named("scriptBean")
@Stateful
@ViewAccessScoped
public class ScriptBean {

	@Inject
	@Log
	private Logger log;
	
	private static final String SCRIPTING_LANGUAGE = "groovy";
	
	private ScriptEngine engine;

	private String scriptContent;
	
	private String scriptOutput;

	@PostConstruct
	public void init() {
		//launchEngine();
	}

	private void launchEngine() {
		final ScriptEngineManager factory = new ScriptEngineManager();
		engine = factory.getEngineByName(SCRIPTING_LANGUAGE);
		log.info("Engine initialized");
	}

	public void avaliar() throws ScriptException {
		if(engine == null) {
			launchEngine();
		}
		
		if (null == scriptContent) {
			return;
		}
		String output = null;
		String error = null;

		try {
			engine.getContext().setWriter(new StringWriter());
			engine.getContext().setErrorWriter(new StringWriter());

			engine.eval(scriptContent);

			output = engine.getContext().getWriter().toString();
			
			setScriptOutput(output);
			
		} catch (final Exception e) {
			throw new ScriptException("Script evaluation error");
		} finally {
			log.info("Script avaliado com sucesso.");
		}
	}

	public List<String> complete(final CompleteEvent event) {
		final Set<String> suggestions = new HashSet<String>();
		final String context = (event.getContext() == null || "null".equalsIgnoreCase(event.getContext())) ? ""
				: StringUtils.reverseDelimited(event.getContext(), '.') + ".";
		final String tokenToBeSearched = context + event.getToken();
		final int level = tokenToBeSearched.contains(".") ? StringUtils.countMatches(tokenToBeSearched, ".") : 0;
		final List<String> keywords = new ArrayList<>(); // helperBean.getKeywordsByLevel().get(level);
		keywords.add("teste");
		keywords.add("palavra");

		if (keywords != null) {
			for (final String keyword : keywords) {
				if (keyword.startsWith(tokenToBeSearched)) {
					suggestions.add(keyword.contains(".") ? keyword.substring(tokenToBeSearched.lastIndexOf(".") + 1)
							: keyword);
				}
			}
		}
		return new ArrayList<>(suggestions);
	}

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}

	public String getScriptOutput() {
		return scriptOutput;
	}

	public void setScriptOutput(String scriptOutput) {
		this.scriptOutput = scriptOutput;
	}

}
