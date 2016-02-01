package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by Administrator on 15-5-27.
 * �������ӿ�
 */
public interface Parser {

    /**
     * ��Document���н���
     * @param document
     * @return
     */
    public Document denoiseForDoc(Document document);

    /**
     * ��λ���������Ĳ���
     * @param document
     * @return
     */
    public Element excavateContent(Document document);

    /**
     * �����Ĳ��ֽ���
     * @param contentElement
     */
    public void denioseForContentElement(Element contentElement);

    /**
     * ����ͼƬ
     * @param contentElement
     */
    public void downloadImg(Element contentElement);

    /**
     * ���������Ƴ������ж���Ĳ���
     * @return
     */
    public String removeNeedlessChars(String contentStr);

    /**
     * �Ƴ�β������
     * @param contentStr
     * @return
     */
    public String removeTails(String contentStr);

    /**
     * ��contentStr���¸�ʽ��ΪElement
     * @param contentStr
     * @return
     */
    public Element format(String contentStr);

    /**
     * ��ȡ����
     * @param document
     * @return
     */
    public String getContent(Document document);

    /**
     * ��ȡ����Element
     * @param document
     * @return
     */
    public Element getContentEle(Document document);

    /**
     * ��ȡ�����ı���������Html��ǩ��
     * @param document
     * @return
     */
    public String getContentText(Document document);

    public String getContentPath();

}
