package org.xs.spider.Fetcher;

/**
 * Created by Administrator on 15-5-29.
 */
public interface ImgFetcher {

    /**
     * ͼƬ������������ͼƬUrl����ͼƬ�����洢��ͼƬ�������ϣ�Ȼ�󷵻���Ӧ��URL
     * @param imgUrl ��Ҫ���ص�ͼƬUrl
     * @return �洢�����غ��ͼƬURL
     */
    public String fetch(String imgUrl);
}
