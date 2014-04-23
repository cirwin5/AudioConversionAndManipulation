/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package amplitudeconverter;

import java.io.File;

/**
 *
 * @author Main
 */
public class AmplitudeConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        WriteExample.WriteExample("WriteExample.wav");
//        System.out.println("HERE");
        try
      {
         // Open the wav file specified as the first argument
//         WavFile wavFile = WavFile.openWavFile(new File(args[0]));
                  WavFile wavFile = WavFile.openWavFile(new File("TwoMoons-bak.wav"));


//          Display information about the wav file
         wavFile.display();

//          Get the number of audio channels in the wav file
         int numChannels = wavFile.getNumChannels();

//          Create a buffer of 100 frames
         double[] buffer = new double[100 * numChannels];

         int framesRead;
         double min = Double.MAX_VALUE;
         double max = Double.MIN_VALUE;

         do
         {
//             Read frames into buffer
            framesRead = wavFile.readFrames(buffer, 100);

//             Loop through frames and look for minimum and maximum value
            for (int s=0 ; s<framesRead * numChannels ; s++)
            {
               if (buffer[s] > max) max = buffer[s];
               if (buffer[s] < min) min = buffer[s];
//               ####################BULLSHIT##################
               buffer[s] = buffer[s] * (0.6);
            }
         }
         while (framesRead != 0);

//          Close the wavFile
         wavFile.close();

//          Output the minimum and maximum value
         System.out.printf("Min: %f, Max: %f\n", min, max);
         
         
         WavFile wavFileTest = WavFile.openWavFile(new File("TwoMoons.wav"));
         int numChannelsTest = wavFile.getNumChannels();
                  double[] bufferTest = new double[100 * numChannels];

         
         
      }        
      catch (Exception e)
      {
         System.err.println(e);
      }
    }
}
