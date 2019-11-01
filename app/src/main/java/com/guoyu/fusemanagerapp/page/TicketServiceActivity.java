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

public class TicketServiceActivity extends BaseActivity {
    private Context context = TicketServiceActivity.this;
//    private TicketHlistAdapter adapter;
//    private TickerSlistAdapter adapters;
//    private List<TicketServiceListBean.DataBean> mList;
//    private List<TicketServiceListBean.DataBean> mList2;
//    @BindView(R.id.empty_order_bloacks)
//    RelativeLayout empty_order_bloacks;
//    @BindView(R.id.empty_order_bloackss)
//    RelativeLayout empty_order_bloackss;
//    @BindView(R.id.refreshs)
//    SmartRefreshLayout refreshs;
//    @BindView(R.id.recycler_view)
//    RecyclerView recycler_view;
//    @BindView(R.id.recycler_viewj)
//    RecyclerView recycler_view2;
//    @BindView(R.id.et_titles)
//    EditText et_titles;
//    private int page = 1;

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.pro)
    ProgressBar progressBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_service);

        url = getIntent().getStringExtra("url");
        ButterKnife.bind(TicketServiceActivity.this);
//        initHlist();
//        initSList();
//        initRefreshs();
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

        webview.addJavascriptInterface(new TicketServiceActivity.JsInterface(), "android");

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

//    private void initRefreshs() {
//        refreshs.setRefreshHeader(new MaterialHeader(TicketServiceActivity.this
//        ));
//        refreshs.setRefreshFooter(new ClassicsFooter(TicketServiceActivity.this));
//        refreshs.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("pageSize", "10");
//                map.put("pageNum", "1");
//                ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Gson gson = new Gson();
//                        TicketServiceListBean bean = gson.fromJson(s, TicketServiceListBean.class);
//                        mList2.clear();
//                        mList2.addAll(bean.getData());
//                        adapters.notifyDataSetChanged();
//                        page = 2;
//                        refreshLayout.finishRefresh(500);
//                    }
//                });
//            }
//        });
//        refreshs.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
//                Map<String, String> map = new LinkedHashMap<>();
//                map.put("pageNum", page + "");
//                map.put("pageSize", "10");
//                ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Gson gson = new Gson();
//                        TicketServiceListBean bean = gson.fromJson(s, TicketServiceListBean.class);
//                        mList2.addAll(bean.getData());
//                        adapters.notifyDataSetChanged();
//                        page = page + 1;
//                        refreshLayout.finishLoadMore(500);
//                    }
//                });
//            }
//        });
//    }
//
//    private void initHlist() {
//        ViseUtil.Get(context, NetUrl.AppEntranceTicketInfofindAllNew, null, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//                Gson gson = new Gson();
//                TicketServiceListBean bean = gson.fromJson(s, TicketServiceListBean.class);
//                mList = bean.getData();
//                if (mList.size() > 0) {//empty_order_bloackss
//                    adapter = new TicketHlistAdapter(mList);
//                    LinearLayoutManager manager = new LinearLayoutManager(context);
//                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    recycler_view.setLayoutManager(manager);
//                    recycler_view.setAdapter(adapter);
//                    empty_order_bloackss.setVisibility(View.GONE);
//                    recycler_view.setVisibility(View.VISIBLE);
//                } else {
//                    empty_order_bloackss.setVisibility(View.VISIBLE);
//                    recycler_view.setVisibility(View.GONE);
//                }
//
//            }
//        });
//    }
//
//    private void initSList() {
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("pageSize", "10");
//        map.put("pageNum", "1");
//        ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
//            @Override
//            public void onReturn(String s) {
//                Log.e("454545454545",s);
//                Gson gson = new Gson();
//                TicketServiceListBean bean = gson.fromJson(s, TicketServiceListBean.class);
//                mList2 = bean.getData();
//                if (mList2.size() > 0) {
//                    adapters = new TickerSlistAdapter(mList2);
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
//
//    }
//
//    private void show_search() {
//        String s = et_titles.getText().toString();
//        if (s.isEmpty()) {
//            ToastUtil.showShort(TicketServiceActivity.this, "请填写要搜索的内容!");
//        } else {
//            Map<String, String> map = new LinkedHashMap<>();
//            map.put("pageSize", "10");
//            map.put("pageNum", "1");
//            map.put("title", s);
//            ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
//                @Override
//                public void onReturn(String s) {
//                    Gson gson = new Gson();
//                    TicketServiceListBean bean = gson.fromJson(s, TicketServiceListBean.class);
//                    mList2 = bean.getData();
//                    if (mList2.size() > 0) {
//                        adapters = new TickerSlistAdapter(mList2);
//                        LinearLayoutManager manager = new LinearLayoutManager(context) {
//                            @Override
//                            public boolean canScrollVertically() {
//                                return false;
//                            }
//                        };
//                        manager.setOrientation(LinearLayoutManager.VERTICAL);
//                        recycler_view2.setLayoutManager(manager);
//                        recycler_view2.setAdapter(adapters);
//                        empty_order_bloacks.setVisibility(View.GONE);
//                        refreshs.setVisibility(View.VISIBLE);
//                        page = 2;
//                    } else {
//                        empty_order_bloacks.setVisibility(View.VISIBLE);
//                        refreshs.setVisibility(View.GONE);
//                    }
//
//                }
//            });
//        }
//
//    }

    @OnClick({R.id.iv_black, R.id.rr_add})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context, TicketInserActivity.class);
                context.startActivity(intent);
                break;
//            case R.id.iv_btn:
//                show_search();
//                break;
        }
    }
}
