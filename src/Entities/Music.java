package Entities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.net.URL;

public class Music {
    private Clip clip;
    private FloatControl volumeControler;
    private JSlider musicVolume;

    public Music(JSlider volume) {
        this.musicVolume = volume;
        try {
            URL url = this.getClass().getResource("/music/fur-elise.wav");
            if (url != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                volumeControler = (FloatControl)
                clip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                JOptionPane.showMessageDialog(null, "File is not found", "Sound Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playMusic() {
        clip.setFramePosition(0);
        setVolume();
        clip.start();
    }
    public void setVolume() {
        float min = volumeControler.getMinimum();    // ������ ~ �80?��
        float max = volumeControler.getMaximum();    // ������ ~ +6?��

        int sliderMin = musicVolume.getMinimum();    // ������ = 1
        int sliderMax = musicVolume.getMaximum();    // ������ = 10

        // ������������� ��������� [0�1]
        float ratio = (musicVolume.getValue() - sliderMin)
                / (float)(sliderMax - sliderMin);

        // ������������� ��������� � ��
        float dB = min + (max - min) * ratio;

        // �� ������ � �������� � ���������� ���������
        dB = Math.max(min, Math.min(max, dB));

        volumeControler.setValue(dB);
    }
    public void musicLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopMusic() {
        clip.stop();
        clip.close();
    }
}
