package ${package}.action;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lumi.action.LumiActionSupport;
import ${package}.service.SampleService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Blocked;

/**
 * Actionクラスのテンプレートサンプル。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 */
@Namespace("/")
@ParentPackage("lumi-default")
@Results({
	// location属性に指定したhtmlファイル名は、/WEB-INF/content 以下からの相対パス。
	@Result(name = ActionSupport.SUCCESS, location = "index" , type="thymeleaf"),
})
@Controller
@Scope("prototype")
@Slf4j
public class SampleAction extends LumiActionSupport {

	/**
	 * デフォルトアクション。
	 */
	@Action("")
	public String start() throws Exception {

		// Serviceクラスの呼び出し
		// List<Map<String,Object>> resultList = service.execute("param");

		// 画面上部に表示するテキスト
		sample = "Actionを実行しました。";

		// Result値。ActionSupportの定数値を返すか、別途定義した値を返すこと。
		// この値は@Resultで指定したname値となる。
		return SUCCESS;
	}

	/**
	 * Actionクラスから実行する業務ロジックのServiceクラス。Autowiredがついたフィールドが自動的に対象となる。
	 */
	@Blocked
	@Autowired
	@Getter @Setter
	private SampleService service;

	@Getter @Setter
	private String sample;

	@Getter @Setter
	private List<Map<String,Object>> resultList;
}
