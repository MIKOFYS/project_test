package MySSM.IOC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanElementNodeObjectFactoryByXML implements BeanElementNodeObjectFactory {
    private Map<String,Object> beanElementNodeObjectMap=new HashMap<>();
    @Override
    public Object getBeanElementObjectById(String id) {
        return this.beanElementNodeObjectMap.get(id);
    }


    public BeanElementNodeObjectFactoryByXML() {
        try{
            //加载配置文件
            InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("Properties\\application.xml");
            //创建DocumentBuilderFactory工厂
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            //工厂产生DocumentBuilder对象
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            //产生Document对象,Document来自包org.w3c.dom
            Document document =documentBuilder.parse(inputStream);
            //获取所有bean结点
            NodeList beanNodeList=document.getElementsByTagName("bean");
            int beanNodeListLen=beanNodeList.getLength();
            for (int i = 0; i < beanNodeListLen; i++) {
                Node beanNode=beanNodeList.item(i);
                //对其中的元素结点进行操作，因为xml中的结点类型有文本结点等其他结点，元素结点是具有标签的结点
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElementNode=(Element) beanNode;
                    String beanElementNodeId=beanElementNode.getAttribute("id");
                    String beanElementNodeClassName=beanElementNode.getAttribute("class");

                    if(this.beanElementNodeObjectMap.get(beanElementNodeId)==null){
                        //获取配置文件中对应id的类对象
                        Object beanElementNodeObject=Class.forName(beanElementNodeClassName).newInstance();
                        //将bean实例对象保存到beanElementNodeObjectMap容器中
                        this.beanElementNodeObjectMap.put(beanElementNodeId,beanElementNodeObject);
                    }
                }
            }
            //建立beanElementNodeObject之间的依赖关系
            for (int i = 0; i < beanNodeListLen; i++) {
                Node beanNode=beanNodeList.item(i);
                //对其中的元素结点进行操作，因为xml中的结点类型有文本结点等其他结点，元素结点是具有标签的结点
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElementNode=(Element) beanNode;
                    String beanElementNodeId=beanElementNode.getAttribute("id");
                    NodeList beanChildNodeList=beanElementNode.getChildNodes();
                    int beanChildNodeListLen=beanChildNodeList.getLength();
                    for (int j = 0; j < beanChildNodeListLen; j++) {
                        Node beanChildNode=beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()==Node.ELEMENT_NODE&&beanChildNode.getNodeName().equals("property")){
                            Element propertyElement=(Element) beanChildNode;
                            String propertyElementName=propertyElement.getAttribute("name");
                            String propertyElementRef=propertyElement.getAttribute("ref");
                            //1）找到propertyElementRef对应的实例
                            Object propertyElementObject=this.beanElementNodeObjectMap.get(propertyElementRef);
                            //2）将propertyElementRef对应的实例设置到对应组件的property属性上去
                            Object beanElementNodeObject=this.beanElementNodeObjectMap.get(beanElementNodeId);
                            //找到对应组件的Class对象,需要通过它运用反射找到其组件的属性字段
                            Class beanElementNodeClass=beanElementNodeObject.getClass();
                            Field propertyField=beanElementNodeClass.getDeclaredField(propertyElementName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanElementNodeObject,propertyElementObject);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
