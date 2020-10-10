package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WebCrawler {

	private String HOST = null;
	private Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
	private Queue<String> q = new ConcurrentLinkedQueue<>();
	private ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 8, 100, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(20));
	
	public List<String> MultiThreadWebCrawler(String startUrl, HtmlParser htmlParser){
		
		HOST = getHost(startUrl);
		set.add(startUrl);
		//List<String> urls = htmlParser.getUrls(startUrl);

		while(!q.isEmpty() || pool.getActiveCount()>0) {
			if(!q.isEmpty()) {			
				String url = q.poll();
			
				Runnable crawlerTask = new CrawlWorker(url, htmlParser, q, set);
				pool.execute(crawlerTask);
			}else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		pool.shutdown();
        try {
			pool.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            if (Objects.nonNull(pool)) {
                pool.shutdownNow();
                //LOGGER.info("shutdown finished");
            }		
		}
		
		return new ArrayList<>(set);
	}
	
	public void getUrls(String startUrl, HtmlParser htmlParser) {
		
		
	}
	
	
	public boolean isValidUrl(String url) {
		String host = getHost(url);
		if(!set.contains(url) && host.contentEquals(HOST)) {
			return true;
		}else {
			return false;
		}
	}

	
	public String getHost(String url) {
		int thirdSlashIdx = url.indexOf('/', 7);
		String host = thirdSlashIdx==-1?url:url.substring(7, thirdSlashIdx);
		return host;
	}

	
	class CrawlWorker implements Runnable {

		private String startUrl;

		private HtmlParser htmlParser;
		
		private Queue<String> q;
		
		private Set<String> set;

		CrawlWorker(String startUrl, HtmlParser htmlParser, Queue<String> q, Set<String> set){
			this.startUrl = startUrl;
			this.htmlParser = htmlParser;
			this.q = q;
			this.set = set;
		}

		@Override
		public void run() {
			parse();
		}

		private void parse(){
			set.add(startUrl);
			List<String> urlList = htmlParser.getUrls(startUrl);
			for(String url : urlList){
				if(set.contains(url) || !getHost(url).equals(HOST)) continue;
				q.offer(url);
			}

		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class HtmlParser {
		
		public List<String> getUrls(String url) {
			return new ArrayList<>();
		}
		
	}
	
}
