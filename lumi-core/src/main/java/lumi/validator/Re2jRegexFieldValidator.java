/**
 *
 */
package lumi.validator;

import org.apache.commons.lang3.StringUtils;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * RE2/J Regular Expression Field Validator.
 * @author A-pZ
 *
 */
public class Re2jRegexFieldValidator extends FieldValidatorSupport {

	private static final Logger LOG = LoggerFactory
			.getLogger(Re2jRegexFieldValidator.class);
	private String regex;
	private String regexExpression;
	private Boolean caseSensitive = Boolean.valueOf(true);
	private String caseSensitiveExpression = "";
	private Boolean trim = Boolean.valueOf(true);
	private String trimExpression = "";


	/* (非 Javadoc)
	 * @see com.opensymphony.xwork2.validator.Validator#validate(java.lang.Object)
	 */
	@Override
	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		Object value = getFieldValue(fieldName, object);

		String regexToUse = getRegex();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Defined regexp as [#0]", new String[] { regexToUse });
		}
		if ((value == null) || (regexToUse == null)) {
			return;
		}
		if (!(value instanceof String)) {
			return;
		}
		String str = ((String) value).trim();
		if (str.length() == 0) {
			return;
		}
		Pattern pattern;
		if (isCaseSensitive()) {
			pattern = Pattern.compile(regexToUse);
		} else {
			pattern = Pattern.compile(regexToUse, 2);
		}
		String compare = (String) value;
		if (isTrimed()) {
			compare = compare.trim();
		}
		Matcher matcher = pattern.matcher(compare);
		if (!matcher.matches()) {
			addFieldError(fieldName, object);
		}
	}

	public String getRegex() {
		if (StringUtils.isNotEmpty(this.regex)) {
			return this.regex;
		}
		if (StringUtils.isNotEmpty(this.regexExpression)) {
			return (String) parse(this.regexExpression, String.class);
		}
		return null;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public void setRegexExpression(String regexExpression) {
		this.regexExpression = regexExpression;
	}

	public boolean isCaseSensitive() {
		if (StringUtils.isNotEmpty(this.caseSensitiveExpression)) {
			return ((Boolean) parse(this.caseSensitiveExpression, Boolean.class))
					.booleanValue();
		}
		return this.caseSensitive.booleanValue();
	}

	public void setCaseSensitive(Boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void setCaseSensitiveExpression(String caseSensitiveExpression) {
		this.caseSensitiveExpression = caseSensitiveExpression;
	}

	public boolean isTrimed() {
		if (StringUtils.isNotEmpty(this.trimExpression)) {
			return ((Boolean) parse(this.trimExpression, Boolean.class))
					.booleanValue();
		}
		return this.trim.booleanValue();
	}

	public void setTrim(Boolean trim) {
		this.trim = trim;
	}

	public void setTrimExpression(String trimExpression) {
		this.trimExpression = trimExpression;
	}

}
