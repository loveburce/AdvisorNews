package com.dawn.apollo.apollo.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.dawn.apollo.apollo.utils.CommonUtils;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class VolleyListener<T extends Object> {
    private static Response.Listener mListener;
    private static Response.ErrorListener mErrorListener;
    private static final String TAG = "VolleyListenner";
    private Type mType;
    private static final int TYPE_STRING = 0;
    private static final int TYPE_JSON = 1;
    private static final int TYPE_UNKONWN = -1;

    public VolleyListener() {
        setTarget();
    }

    public abstract void onComplete();

    public abstract void onSuccess(T result);

    public abstract void onError(VolleyError error);

    public final Response.Listener sucessListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                try {
                    switch (handleFactoty()) {
                        case TYPE_STRING: {
                            onSuccess((T) result);
                            break;
                        }
                        case TYPE_JSON: {
                            try {
                                onSuccess((T) CommonUtils.gson.fromJson(result, mType));
                            } catch (Exception e) {
                                handleException(e);
                            }
                            break;
                        }
                        default: {
                            throw new RuntimeException("Unkonwn type");
                        }
                    }
                    onComplete();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        };
        return mListener;
    }

    public final Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Exception exception = error;
                handleException(exception);
                try {
                    onError(error);
                    onComplete();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        };
        return mErrorListener;
    }

    private void setTarget() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof Class) {
            throw new RuntimeException("Missing type params");
        }
        if (type instanceof ParameterizedType) {
            try {
                Type temp = ((ParameterizedType) type).getActualTypeArguments()[0];
                if ((temp instanceof ParameterizedType)) {
                    Class rawType = (Class) ((ParameterizedType) temp).getRawType();
                    if (List.class.equals(rawType) || ArrayList.class.equals(rawType)) {
                        mType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    }
                } else {
                    mType = ((ParameterizedType) type).getActualTypeArguments()[0];
                }
            } catch (TypeNotPresentException e) {
                e.printStackTrace();
            } catch (MalformedParameterizedTypeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            return;
    }


    private int handleFactoty() {
        if (mType == String.class || mType == Object.class)
            return TYPE_STRING;
        else if (mType != null)
            return TYPE_JSON;
        else
            return TYPE_UNKONWN;
    }


    private void handleException(Exception exception) {
        String errorMessage;
        try {
            throw exception;
        } catch (TimeoutError e) {
            errorMessage = "time out!";
        } catch (NetworkError e) {
            errorMessage = "network failure !";
        } catch (ServerError e) {
            errorMessage = "Server Signal Failure !";
        } catch (AuthFailureError e) {
            errorMessage = " Authentication failed !";
            //other logic
        } catch (VolleyError e) {
            errorMessage = "Request Error !";
        } catch (com.google.gson.JsonSyntaxException e) {
            errorMessage = "data abnormal !";
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            errorMessage = "Unknown Error !";
            Log.e(TAG, e.getMessage());
        }
        CommonUtils.showToast(errorMessage);
    }
}
