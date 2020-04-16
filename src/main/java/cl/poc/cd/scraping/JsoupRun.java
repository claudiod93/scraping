package cl.poc.cd.scraping;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

import static org.jsoup.Connection.Method.GET;
import static org.jsoup.Connection.Method.POST;

public class JsoupRun {

    public static void main (String[] args){
        final String urlLoginPAge = "https://www.afpmodelo.cl/AFP/Home.aspx";
        final String loginAction = "https://www.afpmodelo.cl/AFP/Home.aspx";
        final String accountStatus = "https://www.afpmodelo.cl/Portal-Afiliado/Mi-cuenta/Estado-de-cuenta.aspx";
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
                "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
        final String username = ""; //TODO
        final String password = ""; //TODO

        try {
            HashMap<String, String> formData = new HashMap<>();

            formData.put("ScriptManager1", "UpdatePanel2|Btn_Ingresar");
            formData.put("__EVENTTARGE:", "");
            formData.put("__EVENTARGUMENT", "");
            formData.put("__VIEWSTATE", "/wEPDwUJNzEwNDI4NDgwD2QWBGYPFgIeB1Zpc2libGVoZAIBD2QWCAIFD2QWAmYPZBYEAgMPDxYEHghDc3NDbGFzcwUMZm9ybS1jb250cm9sHgRfIVNCAgJkZAIHDw8WBB8BBQxmb3JtLWNvbnRyb2wfAgICZGQCEw8WAh4JaW5uZXJodG1sBQVhYnJpbGQCFQ8WAh8DBQQyMDIwZAIXDw8WAh4EVGV4dAUJQUZQLVdFQjAxZGRke2OSz3VQEj3cFwE8sOf11lwl2ms=");
            formData.put("__VIEWSTATEGENERATOR", "CE3C48B7");
            formData.put("__EVENTVALIDATION", "/wEdAAsc/WAJG4gQsH3NavDvTmGtBgvkmYP2Xx2FOJPkrH8/e2Q5/Pjgrtqv2sNYvqBd3HtyDxGCS9cLmYXVm4HIrHEvSZ/0k3dtoRjlPxMCuzJiCa+nP2WZW7fr8/uc1t1///g9r5I/iWm00anKsdZws1kB4BSMdlsm6k5Xfa++6jFxcHF8/hop2YhT9w26QFiKu2/4IP4PfpWX0sWEy/NzWP8DRtbOxbYYeDpmbGH8GXM6a4z8Tyolt+m38MobsukfLfpnqY5K");
            formData.put("Login_Rut", username);
            formData.put("Login_Clave", password);
            formData.put("Login_Rut_mob", "");
            formData.put("Login_Clave_mob", "");
            formData.put("q", "");
            formData.put("__ASYNCPOST", "");
            formData.put("Btn_Ingresar", "Ingresar");

            final Connection.Response loginForm = Jsoup.connect(urlLoginPAge).method(GET).execute();
            final Document parse = loginForm.parse();

            HashMap<String, String> cookies = new HashMap<>(loginForm.cookies());

            Connection.Response execute = Jsoup.connect(loginAction).
                    cookies(cookies).
                    data(formData).
                    method(POST).
                    userAgent(USER_AGENT).
                    execute();

            System.out.println(execute.parse().html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
