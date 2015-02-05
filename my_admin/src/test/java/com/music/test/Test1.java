package com.music.test;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;











import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.music.model.WebUser;
import com.music.service.WebUserService;
import com.music.utils.SystemUtil;
import com.music.core.model.Music;
import com.music.core.model.PageObject;
import com.music.core.service.MusicService;



@ContextConfiguration(locations={"/applicationContext.xml","/dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Test1 {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private MusicService musicService;
	
	@Test
	public void testJdbc(){
		
		String sql = "insert into admin_user(username,password) values(?,?)";
		int rt = jdbc.update(sql, "admin2","administrator");
		
	}

	@Test
	public void listPage(){
		PageObject<Music> page = musicService.listByPage(1);
		System.out.println("∑÷“≥≤È—Ø--  " + page.getList().size());
	}
	
	@Test
	public void addWebUser(){
		WebUser wu = null;
		
		for(int i=0; i<50; i++){
			wu = new WebUser();
			wu.setUserName(SystemUtil.getRandomStr(5));
			wu.setPassword("pwd_" + SystemUtil.getRandomStr(6));
			wu.setEmail(SystemUtil.getRandomStr(3)+"@37.com");
			wu.setSex(2);
			webUserService.add(wu);
		}
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("E:/audio_lib");
		File[] files =  file.listFiles();
		DecimalFormat df = new DecimalFormat("00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		for(File f : files){
//			String s = f.getName();
//			System.out.println(s.substring(0, s.indexOf("-")));
//			System.out.println(s.substring(s.indexOf("-")+1,s.lastIndexOf(".")));
//			System.out.println(s.substring(s.lastIndexOf(".")+1));
			System.out.println(f.getAbsolutePath());
			System.out.println(f.getPath());
			System.out.println(f.getCanonicalPath());
			/*try {
				MP3File mp3 = (MP3File) AudioFileIO.read(f);
				MP3AudioHeader header =  (MP3AudioHeader) mp3.getAudioHeader();
				int sec = header.getTrackLength();
				String miniute = df.format(sec/60) + ":" + df.format(sec%60);
				System.out.println(miniute);
			} catch (CannotReadException | IOException | TagException
					| ReadOnlyFileException | InvalidAudioFrameException e) {
				e.printStackTrace();
			} */
			
			
		}
	}
	
}
