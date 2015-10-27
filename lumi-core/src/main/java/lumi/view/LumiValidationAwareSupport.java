/**
 *
 */
package lumi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.interceptor.ValidationAware;

/**
 * ValidationAware拡張。
 * 標準のValidationAwareにWarningメッセージを管理する処理を追加。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public class LumiValidationAwareSupport implements ValidationAware, Serializable {
    private Collection<String> actionWarnings;

    public synchronized void setActionWarnings(Collection<String> errorMessages) {
        this.actionWarnings = errorMessages;
    }

    public synchronized Collection<String> getActionWarnings() {
        return new ArrayList<String>(internalGetActionWarnings());
    }

    public synchronized void addActionWarning(String anWarningMessage) {
        internalGetActionWarnings().add(anWarningMessage);
    }

    public synchronized boolean hasActionWarnings() {
        return (actionWarnings != null) && !actionWarnings.isEmpty();
    }

    public synchronized void crearActionWarnings() {
        internalGetActionWarnings().clear();
    }

    private Collection<String> internalGetActionWarnings() {
        if (actionWarnings == null) {
            actionWarnings = new ArrayList<String>();
        }
        return actionWarnings;
    }

    private Collection<String> actionErrors;
    private Collection<String> actionMessages;
    private Map<String, List<String>> fieldErrors;

    public synchronized void setActionErrors(Collection<String> errorMessages) {
        this.actionErrors = errorMessages;
    }

    public synchronized Collection<String> getActionErrors() {
        return new ArrayList<String>(internalGetActionErrors());
    }

    public synchronized void setActionMessages(Collection<String> messages) {
        this.actionMessages = messages;
    }

    public synchronized Collection<String> getActionMessages() {
        return new ArrayList<String>(internalGetActionMessages());
    }

    public synchronized void setFieldErrors(Map<String, List<String>> errorMap) {
        this.fieldErrors = errorMap;
    }

    public synchronized Map<String, List<String>> getFieldErrors() {
        return new LinkedHashMap<String, List<String>>(internalGetFieldErrors());
    }

    public synchronized void addActionError(String anErrorMessage) {
        internalGetActionErrors().add(anErrorMessage);
    }

    public synchronized void addActionMessage(String aMessage) {
        internalGetActionMessages().add(aMessage);
    }

    public synchronized void addFieldError(String fieldName, String errorMessage) {
        final Map<String, List<String>> errors = internalGetFieldErrors();
        List<String> thisFieldErrors = errors.get(fieldName);

        if (thisFieldErrors == null) {
            thisFieldErrors = new ArrayList<String>();
            errors.put(fieldName, thisFieldErrors);
        }

        thisFieldErrors.add(errorMessage);
    }

    public synchronized boolean hasActionErrors() {
        return (actionErrors != null) && !actionErrors.isEmpty();
    }

    public synchronized boolean hasActionMessages() {
        return (actionMessages != null) && !actionMessages.isEmpty();
    }

    public synchronized boolean hasErrors() {
        return (hasActionErrors() || hasFieldErrors());
    }

    public synchronized boolean hasFieldErrors() {
        return (fieldErrors != null) && !fieldErrors.isEmpty();
    }

    private Collection<String> internalGetActionErrors() {
        if (actionErrors == null) {
            actionErrors = new ArrayList<String>();
        }

        return actionErrors;
    }

    private Collection<String> internalGetActionMessages() {
        if (actionMessages == null) {
            actionMessages = new ArrayList<String>();
        }

        return actionMessages;
    }

    private Map<String, List<String>> internalGetFieldErrors() {
        if (fieldErrors == null) {
            fieldErrors = new LinkedHashMap<String, List<String>>();
        }

        return fieldErrors;
    }

    /**
     * Clears field errors map.
     * <p/>
     * Will clear the map that contains field errors.
     */
    public synchronized void clearFieldErrors() {
        internalGetFieldErrors().clear();
    }

    /**
     * Clears action errors list.
     * <p/>
     * Will clear the list that contains action errors.
     */
    public synchronized void clearActionErrors() {
        internalGetActionErrors().clear();
    }

    /**
     * Clears messages list.
     * <p/>
     * Will clear the list that contains action messages.
     */
    public synchronized void clearMessages() {
        internalGetActionMessages().clear();
    }

    /**
     * Clears all error list/maps.
     * <p/>
     * Will clear the map and list that contain
     * field errors and action errors.
     */
    public synchronized void clearErrors() {
        internalGetFieldErrors().clear();
        internalGetActionErrors().clear();
    }

    /**
     * Clears all error and messages list/maps.
     * <p/>
     * Will clear the maps/lists that contain
     * field errors, action errors and action messages.
     */
    public synchronized void clearErrorsAndMessages() {
        internalGetFieldErrors().clear();
        internalGetActionErrors().clear();
        internalGetActionWarnings().clear(); // ワーニング消去を拡張。
        internalGetActionMessages().clear();
    }

}
