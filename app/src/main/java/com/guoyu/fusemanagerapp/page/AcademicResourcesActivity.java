package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcademicResourcesActivity extends BaseActivity {

    private Context context = AcademicResourcesActivity.this;
//    private DisciplineInformationAdapter adapter;
//    private DisciplineInfoAdapter adapters;
//    private List<AcademicResourcesTypeBean.DataBean> mList;
//    private List<AcademicResourcesListBean.DataBean> mList2;
//    private int page = 1;
//    @BindView(R.id.refreshs)
//    SmartRefreshLayout refreshs;
//    @BindView(R.id.recycler_view)
//    RecyclerView recycler_view;
//    @BindView(R.id.recycler_view2)
//    RecyclerView recycler_view2;
//    @BindView(R.id.empty_order_bloacks)
//    RelativeLayout empty_order_bloacks;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.pro)
    ProgressBar progressBar;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_resources);

        url = getIntent().getStringExtra("url");
        ButterKnife.bind(AcademicResourcesActivity.this);
//        initType();
//        initList();
//        initRefreshs();
        initWebview();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initWebview();
    }

    private void initWebview() {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setTextZoom(100);
        webview.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setCacheMode(
                webview.getSettings().LOAD_NO_CACHE); // 缓存设置
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //使用控件ProgressDialog来显示更新进度条示数
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tvTitle.setText(title);
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有网站的证书
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载错误时的回调
            }
        });

        webview.addJavascriptInterface(new AcademicResourcesActivity.JsInterface(), "android");

        webview.loadUrl(url);

    }

    public class JsInterface {
        //JS中调用Android中的方法 和返回值处理的一种方法

        /****
         * Html中的点击事件 onclick
         * <input type="button" value="结算" onclick="showToast('12')">
         *
         * @param
         */
        @JavascriptInterface
        public void toDetail(String targetid) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
                webview.goBack();//后退
                //webview.goForward();//前进
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            //再次打开页面时，若界面没有消亡，会导致进度条不显示并且界面崩溃
            webview.stopLoading();
            webview.onPause();
            webview.clearCache(true);
            webview.clearHistory();
            //动态创建webview调用
            //ViewGroup parent = (ViewGroup) mWebView.getParent();
            //if (parent != null) {
            //  parent.removeView(mWebView);
            //}
            webview.removeAllViews();
            //先结束未结束线程，以免可能会导致空指针异常
            webview.destroy();
            webview = null;
            super.onDestroy();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        initList();
//    }
//
//    private void initRefreshs() {
//        refreshs.setRefreshHeader(new MaterialHeader(AcademicResourcesActivity.this
//        ));
//        refreshs.setRefreshFooter(new ClassicsFooter(AcademicResourcesActivity.this));
//        refreshs.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("pageSize", "10");
//                map.put("pageNum", "1");
//                ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Gson gson = new Gson();
//                        AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
//                        mList2.clear();
//                        mList2.addAll(bean.getData());
//                        adapters.notifyDataSetChanged();
//                        page = 2;
//                    }
//                });
//                refreshLayout.finishRefresh(1000);
//            }
//        });
//        refreshs.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("pageNum", page + "");
//                map.put("pageSize", "10");
//                ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Gson gson = new Gson();
//                        AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
//                        // mList.clear();
//                        mList2.addAll(bean.getData());
//                        adapters.notifyDataSetChanged();
//                        page = page + 1;
//                    }
//                });
//                refreshLayout.finishLoadMore(1000);
//            }
//        });
//    }
//
//    private void initType() {
//        ViseUtil.Get(context, NetUrl.AppEducationInfofindType, null, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//                Gson gson = new Gson();
//                AcademicResourcesTypeBean bean = gson.fromJson(s, AcademicResourcesTypeBean.class);
//                mList = bean.getData();
//                adapter = new DisciplineInformationAdapter(mList);
//                GridLayoutManager manager = new GridLayoutManager(context, 3);
//                recycler_view.setLayoutManager(manager);
//                recycler_view.setAdapter(adapter);
//            }
//        });
//    }
//
//    private void initList() {
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("pageSize", "10");
//        map.put("pageNum", "1");
//        ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//                Gson gson = new Gson();
//                AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
//                mList2 = bean.getData();
//                if (mList2.size() > 0) {
//                    adapters = new DisciplineInfoAdapter(mList2);
//                    LinearLayoutManager manager = new LinearLayoutManager(context) {
//                        @Override
//                        public boolean canScrollVertically() {
//                            return false;
//                        }
//                    };
//                    manager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_view2.setLayoutManager(manager);
//                    recycler_view2.setAdapter(adapters);
//                    empty_order_bloacks.setVisibility(View.GONE);
//                    refreshs.setVisibility(View.VISIBLE);
//                    page = 2;
//                } else {
//                    empty_order_bloacks.setVisibility(View.VISIBLE);
//                    refreshs.setVisibility(View.GONE);
//                }
//
//            }
//        });
//    }

    @OnClick({R.id.iv_black, R.id.rr_add})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context, AcademicResourcesInsertActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
