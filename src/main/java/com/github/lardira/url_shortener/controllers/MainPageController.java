package com.github.lardira.url_shortener.controllers;

import com.github.lardira.url_shortener.models.URL;
import com.github.lardira.url_shortener.services.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

@Controller
@RequestMapping({"", "/"})
public class MainPageController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String index(
            @RequestParam(required = false, name = "inputURL") String inputURL,
            Model model,
            HttpServletRequest request
    ) {
        URL url = new URL(inputURL);

        if (inputURL != null) try {
            //throws MalformedURLException if url is invalid
            urlService.isValidUrl(inputURL);
            url = urlService.find(inputURL);

        } catch (MalformedURLException e) {
            model.addAttribute("error", "the URL is invalid");

        } catch (NoSuchElementException e) {
            urlService.save(url);
            url.setEncodedUrlId(urlService.encode(url));
            url.setEncoded(request.getRequestURL() + url.getEncodedUrlId());

            //Updating with encoded values, it looks like there is a better way
            urlService.save(url);
        } finally {
            model.addAttribute("outputURL", url.getEncoded());
        }
        return "main";
    }

    @GetMapping("{encoded}")
    public String redirect(@PathVariable("encoded") String encoded, Model model) {
        URL url = urlService.findByEncodedUrlId(encoded);
        return "redirect:" + url.getOriginal();
    }
}
