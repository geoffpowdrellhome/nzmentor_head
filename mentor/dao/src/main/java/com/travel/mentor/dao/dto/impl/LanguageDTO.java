package com.travel.mentor.dao.dto.impl;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class LanguageDTO implements Serializable {

	private static final long serialVersionUID = -3863392491172579819L;

	private int id;
	private String lanLocale;
	private String lanText;

	public LanguageDTO() {
	}

	public LanguageDTO(int id, String lanLocale, String lanText) {
		this.setId(id);
		this.setLanLocale(lanLocale);
		this.setLanText(lanText);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setLanLocale(String lanLocale) {
		this.lanLocale = lanLocale;
	}

	public String getLanLocale() {
		return lanLocale;
	}

	public void setLanText(String lanText) {
		this.lanText = lanText;
	}

	public String getLanText() {
		return lanText;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(getId()).hashCode();
	}

	public boolean equals(LanguageDTO language) {
		return getId() == language.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof LanguageDTO) {
			LanguageDTO language = (LanguageDTO) obj;
			return equals(language);
		}

		return false;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
