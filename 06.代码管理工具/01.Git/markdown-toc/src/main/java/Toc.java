import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;

/**
 * <p>Title: Toc</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/3 0003 14:27
 */
public class Toc {
    public static void main(String[] args) {
        AtxMarkdownToc.newInstance().genTocFile("F:\\工作\\记录\\06.代码管理工具\\01.Git\\04.Git--GitHub生成目录方法.md");
    }
}
