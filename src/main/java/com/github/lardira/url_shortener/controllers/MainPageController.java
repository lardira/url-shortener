package com.github.lardira.url_shortener.controllers;

import com.github.lardira.url_shortener.models.URL;
import com.github.lardira.url_shortener.services.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Controller
@RequestMapping({"", "/"})
public class MainPageController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String index(
            @RequestParam(required = false, value = "inputURL") String inputURL,
            Model model,
            HttpServletRequest request
    ) {
        if (inputURL != null)
            try {
                //throws MalformedURLException if url is invalid
                urlService.isValidUrl(inputURL);

                URL url = urlService.find(inputURL);
                if (url == null) {
                    url = urlService.save(new URL(inputURL));
                    url.setEncodedUrlId(urlService.encode(url));
                    url.setEncoded(request.getRequestURL() + url.getEncodedUrlId());

                    //Updating with encoded values, it looks like there is a better way
                    urlService.save(url);
                }
                model.addAttribute("outputURL", url.getEncoded());
            } catch (MalformedURLException e) {
                model.addAttribute("error", "the URL is invalid");
            } catch (Exception e) {
                return "error";
            }
        return "main";
    }

    @GetMapping("{encoded}")
    public String redirect(@PathVariable("encoded") String encoded, Model model) {
        URL url = urlService.getBy(encoded);
        if (url == null) {
            return "error";
        }

        String redirectTo = url.getOriginal();
        System.out.println("redirecting from " + url.getEncoded() + " to " + redirectTo);
        return "redirect:" + redirectTo;
    }
}
