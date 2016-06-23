package com.sunilsahoo.designpatterns;

/**
 * categories: Structural
 * 
 * ## Also known as Wrapper
 * 
 * ## Intent Convert the interface of a class into another interface the clients
 * expect. Adapter lets classes work together that couldn't otherwise because of
 * incompatible interfaces.
 * 
 * 
 * ## Applicability Use the Adapter pattern when
 * 
 * you want to use an existing class, and its interface does not match the one
 * you need you want to create a reusable class that cooperates with unrelated
 * or unforeseen classes, that is, classes that don't necessarily have
 * compatible interfaces you need to use several existing subclasses, but it's
 * impractical to adapt their interface by subclassing every one. An object
 * adapter can adapt the interface of its parent class.
 * 
 * 
 * 
 * Adapter pattern works as a bridge between two incompatible interfaces. This
 * type of design pattern comes under structural pattern as this pattern
 * combines the capability of two independent interfaces. This pattern involves
 * a single class which is responsible to join functionalities of independent or
 * incompatible interfaces. A real life example could be a case of card reader
 * which acts as an adapter between memory card and a laptop. You plugin the
 * memory card into card reader and card reader into the laptop so that memory
 * card can be read via laptop.
 * 
 * ## Real world examples java.util.Arrays#asList(), BaseAdapter
 * 
 * @author sunilkumarsahoo
 *
 */
public class AdapterExample {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();
		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}

interface MediaPlayer {
	public void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
	public void playVlc(String fileName);

	public void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		// do nothing
	}
}

class Mp4Player implements AdvancedMediaPlayer {
	@Override
	public void playVlc(String fileName) {
		// do nothing
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: " + fileName);
	}
}

class MediaAdapter implements MediaPlayer {
	AdvancedMediaPlayer advancedMusicPlayer;

	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer = new VlcPlayer();
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer = new Mp4Player();
		}
	}

	@Override
	public void play(String audioType, String fileName) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer.playMp4(fileName);
		}
	}
}

class AudioPlayer implements MediaPlayer {
	MediaAdapter mediaAdapter;

	@Override
	public void play(String audioType, String fileName) {
		// inbuilt support to play mp3 music files
		if (audioType.equalsIgnoreCase("mp3")) {
			System.out.println("Playing mp3 file. Name: " + fileName);
		}
		// mediaAdapter is providing support to play other file formats
		else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println("Invalid media. " + audioType + " format not supported");
		}
	}
}
