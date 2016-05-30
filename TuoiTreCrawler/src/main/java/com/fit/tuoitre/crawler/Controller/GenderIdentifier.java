/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.Controller;

import com.github.irobson.jgenderize.GenderizeIoAPI;
import com.github.irobson.jgenderize.client.Genderize;
import com.github.irobson.jgenderize.model.NameGender;
import java.util.Locale;

/**
 *
 * @author Corncob
 */
public class GenderIdentifier {

    public static int getGender(String name) {
        Genderize api = GenderizeIoAPI.create();
        NameGender gender = api.getGender(name, new Locale("vi", "VN"));
        String genderString = gender.getGender();
        if(genderString.equals("male"))
            return 0;
        if(genderString.equals("female"))
            return 1;
        return -1;
    }
}
