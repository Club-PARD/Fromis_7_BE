package com.example.fromis_7_be.metadata.service;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MetadataService {

    public Map<String, Object> fetchMetadata(String url) throws IOException {
        Map<String, Object> data = new HashMap<>();

        try {
            // SSLContext를 생성하여 모든 인증서를 신뢰하도록 설정
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial((TrustStrategy) (X509Certificate[] chain, String authType) -> true)
                    .build();

            // SSLConnectionSocketFactory를 NoopHostnameVerifier와 함께 설정
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    NoopHostnameVerifier.INSTANCE);

            // SSLConnectionSocketFactory에 SSLContext와 NoopHostnameVerifier 추가
            CloseableHttpClient client = HttpClients.custom()
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                            .setSSLSocketFactory(sslSocketFactory)
                            .build())
                    .build();

            HttpGet request = new HttpGet(url);

            /** Naver SmartStore 크롤링을 위한 WebDriver 설정
             System.setProperty("webdriver.chrome.driver", "src/main/resources/static/chromedriver");
             ChromeOptions options = new ChromeOptions();
             options.setBinary("/Users/juhyun/Downloads/chrome-mac-arm64/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing");
             //            options.addArguments("--headless","--window-size=1920,1200", "--start-maximized");
             options.addArguments("--window-size=1920,1200", "--start-maximized");

             WebDriver driver = new ChromeDriver(options);

             try (CloseableHttpResponse response = client.execute(request)) {
             driver.get(url);
             // Wait for the page to load fully
             driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

             // Get page source after JavaScript execution
             String pageSource = driver.getPageSource();

             */

            try (CloseableHttpResponse response = client.execute(request)) {
                String html = EntityUtils.toString((HttpEntity) response.getEntity());
                Document doc = Jsoup.parse(html);


                // 기존 메타데이터 추출 로직
                String title = doc.title();
                data.put("title", title);
                // 설명 추출
                String description = doc.select("meta[name=description]").attr("content");
                if (description.isEmpty()) {
                    description = doc.select("meta[property=og:description]").attr("content");
                }
                data.put("description", description);

                // 썸네일 이미지 추출
                String thumbnailUrl = doc.select("meta[property=og:image]").attr("content");
                if (thumbnailUrl.isEmpty()) {
                    thumbnailUrl = doc.select("img").first().attr("src");
                }
                if (thumbnailUrl != null && !thumbnailUrl.startsWith("http")) {
                    thumbnailUrl = new URL(new URL(url), thumbnailUrl).toString();
                }
                data.put("thumbnailUrl", thumbnailUrl);

                // 사이트 이름 추출
                String siteName = doc.select("meta[property=og:site_name]").attr("content");
                data.put("siteName", siteName);

                // 파비콘(Favicon) 추출
                String favicon = doc.select("link[rel=shortcut icon]").attr("href");
                if (favicon.isEmpty()) {
                    favicon = doc.select("link[rel=icon]").attr("href");
                }
                if (favicon != null && !favicon.startsWith("http")) {
                    favicon = new URL(new URL(url), favicon).toString();
                }
                data.put("favicon", favicon);

                // 가격 정보 추출
                String price = doc.select("meta[property=product:price:amount]").attr("content");
                if (price.isEmpty()) {
                    price = doc.select("meta[name=price]").attr("content");
                }
                if (price.isEmpty()) {
                    String[] priceSelectors = {
                            ".price", ".product-price", "#price", ".amount", ".pricing",
                            "[itemprop=price]", ".sale-price", ".discount-price", ".current-price",
                            ".product_price", ".woocommerce-Price-amount"
                    };
                    for (String selector : priceSelectors) {
                        String priceText = doc.select(selector).text();
                        if (!priceText.isEmpty()) {
                            price = priceText;
                            break;
                        }
                    }
                }
                data.put("price", price);

                // 작성자(Author) 추출
                String author = doc.select("meta[name=author]").attr("content");
                if (author.isEmpty()) {
                    author = doc.select("meta[property=article:author]").attr("content");
                }
                data.put("author", author);

                // 게시 날짜 추출
                String publishDate = doc.select("meta[property=article:published_time]").attr("content");
                if (publishDate.isEmpty()) {
                    publishDate = doc.select("meta[name=pubdate]").attr("content");
                }
                data.put("publishDate", publishDate);

                // 키워드 추출
                String keywords = doc.select("meta[name=keywords]").attr("content");
                data.put("keywords", keywords);

                return data;
            } catch (org.apache.hc.core5.http.ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
}
