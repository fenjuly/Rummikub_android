package org.xhome_liurongchan.rummikub.validate;

import java.util.List;

import org.xhome_liurongchan.rummikub.bean.Player;
import org.xhome_liurongchan.rummikub.bean.Rummikub;

/**
 * @author [FeN]July  E-mail: newfenjuly@gmail.com
 * @version 创建时间：2013-6-12 下午2:37:21
 * 类说明:判断能否出牌
 */
public interface isValidate {

	public boolean isFirst(Player player);//判断是否破冰
	
	public int isCardValidate(Player player, List<Rummikub> valueList);//判断出牌是否合理
	
	public int  firstAction(Player player, List<Rummikub> valueList, int count);//判断能否破冰
	
	public boolean nextAction(Player player,List<Rummikub> valueList);//破冰后判断能否出牌
}
