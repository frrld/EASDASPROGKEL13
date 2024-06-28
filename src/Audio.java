import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    // Method to play an audio clip
    public void playSound(String soundFileName) {
        try {
            File audioFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            waitForClipToFinish(clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to wait for the clip to finish playing
    private void waitForClipToFinish(Clip clip) {
        try {
            Thread.sleep(clip.getMicrosecondLength() / 1000); // Sleep for the duration of the clip
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
