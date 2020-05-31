# leafCode_demo
对给定的指定节点，得到其所有的最叶子节点
文件中存在以下数据
code:node1 ;parentCode:node2
code:node2 ;parentCode:node3
code:node4 ;parentCode:node2

每一行分别记录节点的code和父节点code。
要求实现一个方法，对给定的指定节点，得到其所有的最叶子节点（指该节点没有子节点）
List<String> getLeafCode(String code);

备注：
要求尽可能降低时间复杂度，兼顾空间复杂度（假设文件很大，有数百万行）
请描述一下方案的选择思路、功能的伪代码实现


