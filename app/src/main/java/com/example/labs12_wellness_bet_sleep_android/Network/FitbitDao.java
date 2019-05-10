package com.example.labs12_wellness_bet_sleep_android.Network;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class FitbitDao {
    public static final String CODETAG = "CodeTag";
    public static final String AUTHTAG = "AuthTag";

    private String clientid = "22DPQJ";
    private String clientSecret = "f284096b09bb4af7bd4a49b9ddcc1912";
    private String apiCallback = "myapp://logincallback";
    private String apiScope = "weight activity heartrate sleep profile";
    private JSONObject apiSleepAmount, apiValueDistancesPeriod;
    private String authCode;
    private OAuth20Service service;
    private OAuth2AccessToken accessToken, refreshToken;
    private Date date;

    FitbitDao(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    static void sendUserToAuth(Context callingContext) {
        FitbitDao helperDao = new FitbitDao();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(callingContext, Uri.parse(helperDao.getAuthorizationUrl()));
    }

    private String getAuthorizationUrl(){
        return getService().getAuthorizationUrl();
    }

    private OAuth20Service createService(){
        return new ServiceBuilder(clientid)
                .apiSecret(clientSecret)
                .scope(apiScope)
                .callback(apiCallback)
                .build(FitbitApi20.instance());
    }

    private OAuth20Service getService() {
        if (service == null) {
            service = createService();
        }
        return service;
    }

    private void setAuthCodeFromIntent(Uri returnUrl) {
        authCode = returnUrl.getQueryParameter("code");
        Log.d(CODETAG, "Auth Code set to " + authCode);
    }

    private void requestAccessToken(){
        OAuth20Service serviceCall = getService();
        try {
            accessToken = serviceCall.getAccessToken(authCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(AUTHTAG, "Auth Token set to " + accessToken);
    }

    void requestAccessTokenFromIntent(Uri returnUrl){
        setAuthCodeFromIntent(returnUrl);
        requestAccessToken();
    }

    JSONObject makeApiRequest(String endPointUrl)
            throws InterruptedException, ExecutionException, IOException{
        OAuth20Service serviceCall = getService();

        final OAuthRequest activityRequest = new OAuthRequest(Verb.GET,
                "https://api.fitbit.com/1.2/user/-/"+ endPointUrl);
        serviceCall.signRequest(accessToken, activityRequest);

        final Response response;
        response = serviceCall.execute(activityRequest);

        try {
            return new JSONObject(response.getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    JSONObject getSleepDateRange () throws InterruptedException, ExecutionException, IOException{
        if (apiSleepAmount == null){
            apiSleepAmount = makeApiRequest("sleep/date/2019-02-04/2019-05-08.json");
        }
        return apiSleepAmount;
    }
}
