package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xotd.XotdService;

public class XotdServiceImpl extends UnicastRemoteObject implements XotdService {
	public static final long serialVersionUID = 1L;
	public final String xotd_url = "http://www.quotationspage.com/";
	
	public XotdServiceImpl() throws RemoteException { 
		super(); 
	}
	
	@Override
	public String getQotd() throws RemoteException, IOException {
//		Document doc = Jsoup.connect("http://www.quotationspage.com/qotd/previous.html").get();
		Document doc = Jsoup.connect("http://www.quotationspage.com/qotd.html").get();
		Elements quotations = doc.select(".quote a");
		Elements authors = doc.select(".author b a");
		
		String[] res_list = new String[quotations.size()];
		
		int idx = 0;
		for (Element quotation: quotations) { res_list[idx++] = Integer.toString(idx) + ". " + quotation.text(); }
		idx = 0;
		for (Element author: authors) { res_list[idx++] += " -" + author.text() + "-\n"; }
		
		String res = "";
		for (int i = 0; i < idx; ++i) res += res_list[i];
		
		if (res.equals("")) {
			res += "There are no quotation here\n";
		}
		return res;
	}

	@Override
	public String getWotd() throws RemoteException, IOException {
		Document doc = Jsoup.connect("http://www.quotationspage.com/wotd.html").get();
		Elements words = doc.select("#wod_hw");
		Elements parts = doc.select(".WoDPart");
		Elements defs  = doc.select(".WoDDef");
		Elements synos = doc.select(".WoDSyn");
		Elements usages = doc.select(".WoDUsage");
		
		String[] res_list = new String[words.size()];
		
		int idx = 0;
		for (Element word: words) { res_list[idx++] = Integer.toString(idx) + ". Word: " + word.text() + "\n"; }
		idx = 0;
		for (Element part: parts) { res_list[idx++] += "Definition: " + part.text(); }
		idx = 0;
		for (Element def: defs) { res_list[idx++] += " " + def.text() + "\n"; }
		idx = 0;
		for (Element syno: synos) { res_list[idx++] += "Synonyms: " + syno.text() + "\n"; }
		idx = 0;
		for (Element usage: usages) { res_list[idx++] += "Usages: " + usage.text() + "\n"; }
		
		String res = "";
		for (int i = 0; i < idx; ++i) res += res_list[i];
		
		if (res.equals("")) {
			res += "There is no word here\n";
		}
		return res;
	}

}
