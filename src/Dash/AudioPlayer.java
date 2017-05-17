package Dash;

import javax.sound.sampled.*;

public class AudioPlayer {
	
	private Clip clip;
	
/*****************************************************************************************************************************************************************
 * Constructs an audio input stream that reads its data from the target indicated
 * @param filename
 */
	public AudioPlayer(String filename){
		
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filename));
			AudioFormat baseFormat = audio.getFormat();
			AudioFormat decodeFormat =  new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED, 
					baseFormat.getSampleRate(), 
					16, 
					baseFormat.getChannels(), 
					baseFormat.getChannels()*2, 
					baseFormat.getSampleRate(), 
					false);
			
			AudioInputStream decodeAudio = AudioSystem.getAudioInputStream(decodeFormat,audio);
			clip = AudioSystem.getClip();
			clip.open(decodeAudio);
		} 
		
		catch (Exception e) {
			System.out.println("Couldn't load audio");
		} 
	}
	
/*****************************************************************************************************************************************************************
 * This method stops the file that is playing
 */
	public void stopAudio(){
		if(clip.isRunning()){//if the audio is still playing
			clip.stop();//then stop it
		}
	}
	
/*****************************************************************************************************************************************************************
 * This method will play a file as long as it isn't empty
 */
	public void playAudio(){
		if(clip == null){//if the file is empty
			return;//get out of the method
		}
		stopAudio();//stop the file that is playing
		clip.setFramePosition(0);//restart the file
		clip.start();//start audio again
	}
	
/*****************************************************************************************************************************************************************
 * This stops the audio for good
 */
	public void closeMusic(){
		stopAudio();//stop the audio
		clip.close();//and close it
	}
	
/*****************************************************************************************************************************************************************
 * Restart music
 */
	public void restartAudio(){
		playAudio();//method stops and replays the audio
	}
}
