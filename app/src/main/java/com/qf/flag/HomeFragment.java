package com.qf.flag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.qf.adapter.FragNewsPagerAdapter;
import com.qf.carousel.LocalImageHolderView;
import com.qf.carousel.NetworkImageHolderView;
import com.qf.entity.HomeHeadEntity;
import com.qf.sep12bantangexam.R;
import com.qf.util.Constant;
import com.qf.util.DownUtil;
import com.qf.util.JsonUtil;
import com.qf.widget.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2016/9/12.
 */
public class HomeFragment extends Fragment implements DownUtil.OnDownListener {

    //轮播图片相关
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<>();
    private List<String> networkImages;
    private List<String> strList = new ArrayList<>();
    private String[] str;

    //新闻列表Fragment底部内容的ViewPager
    private ViewPager newsvpager;
    private FragNewsPagerAdapter fragNewsPagerAdapter;


    //自定义控件Tab栏
    private TabView tabView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_home, null);
        newsvpager = (ViewPager) view.findViewById(R.id.news_viewpager);
        tabView = (TabView) view.findViewById(R.id.tabview);
        //添加头部轮播图片
        loadCarousel();
        //加载新闻列表数据
        loadNewsDatas();
        return view;
    }

    //加载新闻列表数据
    private void loadNewsDatas() {
        new DownUtil().setOnDownListener(this).downJSON(Constant.URL_HOME_TAB);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null){
            return new Gson().fromJson(json,HomeHeadEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            HomeHeadEntity headEntity = (HomeHeadEntity) object;
            //头部tab栏数据
            List<HomeHeadEntity.DataBean.CategoryElementBean> category_element =
                    headEntity.getData().getCategory_element();
            tabView.setTabs(category_element);

            //底部的ViewPager
            fragNewsPagerAdapter = new FragNewsPagerAdapter(getChildFragmentManager(),category_element);
            newsvpager.setAdapter(fragNewsPagerAdapter);

            //设置Tab栏和Viewpager联动
            tabView.setViewPager(newsvpager);

        }
    }


    //添加头部轮播图片
    private void loadCarousel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonUtil util = retrofit.create(JsonUtil.class);
        Call<HomeHeadEntity> call = util.getpicDatas();
        call.enqueue(new Callback<HomeHeadEntity>() {
            @Override
            public void onResponse(Call<HomeHeadEntity> call, Response<HomeHeadEntity> response) {
                List<HomeHeadEntity.DataBean.BannerBean> list = response.body().getData().getBanner();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPhoto() != null) {
                        strList.add(list.get(i).getPhoto());
                    }

                }
                initImageLoader();

                convenientBanner = (ConvenientBanner) getView().findViewById(R.id.convenientBanner_showGoods);

                //本地图片例子
                convenientBanner.setPages(
                        new CBViewHolderCreator<LocalImageHolderView>() {
                            @Override
                            public LocalImageHolderView createHolder() {
                                return new LocalImageHolderView();
                            }
                        }, localImages)
                        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求
                        .setPageIndicator(new int[]{R.mipmap.ic_indicator_image,
                                R.mipmap.ic_indicator_image_selected})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                //  网络加载例子
                str = new String[strList.size()];
                for (int i = 0; i < strList.size(); i++) {
                    str[i] = strList.get(i);
                }
                networkImages = Arrays.asList(str);
                convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, networkImages);
                convenientBanner.startTurning(2000);
            }

            private void initImageLoader() {

                //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                        showImageForEmptyUri(R.mipmap.ic_launcher)
                        .cacheInMemory(true).cacheOnDisk(true).build();

                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                        getActivity()).defaultDisplayImageOptions(defaultOptions)
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO).build();
                ImageLoader.getInstance().init(config);

            }

            @Override
            public void onFailure(Call<HomeHeadEntity> call, Throwable t) {

            }
        });


    }



}
