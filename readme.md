# Adaptive Huffman Encoding and Decoding
This repository contains an implementation of an **Adaptive Huffman** compression algorithm in Java. It includes functionality for encoding, decoding, and visualizing the Huffman tree during the adaptive compression process.
## Features
1. **Adaptive Compression**:
    - Real-time tree restructuring based on frequency of symbols encountered.
    - Efficient encoding and decoding while maintaining the prefix property.

2. **Huffman Tree Visualization**:
    - A graphical representation of the adaptive Huffman tree during encoding.
    - Supports step-by-step visualization to understand the tree's dynamic structure as characters are processed.

3. **Unit Tests**:
    - Fully unit-tested to ensure correctness and reliability of the compression and tree structure modules.

4. **Bit-level I/O**:
    - Custom `BitInputStream` and `BitByteOutputStream` implementations for reading/writing bits efficiently.

5. **Real-world Input Support**:
    - Encode and decode text files with sample input/output provided.

## Project Structure
- **`adaptiveHuffman/`**
  Contains the core classes for encoding, decoding, and tree visualization:
    - `Tree`: Represents the Adaptive Huffman tree and manages encoding/decoding operations.
    - `Node`: Represents individual nodes in the Huffman tree (internal nodes, NYT nodes, or leaf nodes).
    - `Encoder`: Encodes input text into a compressed format.
    - `Decoder`: Decodes the compressed format back into the original text.
    - `HuffmanTreeVisualizer`: Visualizes the Huffman tree dynamically during encoding.

- **`adaptiveHuffman/tests/`**
  Unit test suite for verifying the tree structure, encoding, decoding, and overall system behavior.
- **I/O Utilities**:
    - `BitInputStream` and `BitByteOutputStream`: Handle reading and writing data at the bit level for compression and decompression.

- **Sample Input/Output**:
    - `input.txt`: Sample input text file for compression.
    - `decoded.txt`: Expected output after decompression of the encoded file.

## How to Run
### Encoding
1. Compile the code using `javac`.
2. Run the `Encoder` class:
``` bash
   java adaptiveHuffman.encoder.Encoder input.txt encoded_output.bin
```
1. Output includes:
    - Compressed file size.
    - Compression ratio.

### Decoding
1. Run the `Decoder` class:
``` bash
   java adaptiveHuffman.decoder.Decoder encoded_output.bin decoded_output.txt
```
1. The `decoded_output.txt` should match the original input file.

### Visualizing
To visualize the adaptive Huffman tree dynamically:
``` bash
java adaptiveHuffman.HuffmanTreeVisualizer
```
The tree will update as the encoding progresses, showing the balance and structure changes.
## Tree Visualization
The **HuffmanTreeVisualizer** is a key feature that provides a detailed real-time view of the tree structure. Node characteristics:
- **NYT Nodes**: Represent new, unseen symbols (blue).
- **Leaf Nodes**: Represent character frequency (green).
- **Internal Nodes**: Standard gray nodes connecting children.

## Testing
Run the provided unit tests using a testing framework (e.g., JUnit).
Example command:
``` bash
java -cp .:junit.jar org.junit.runner.JUnitCore treeTest.treeStructureTest
```
Tests include:
- Initialization of the tree.
- Correct insertion of new characters.
- Handling repeated characters.
- Tree structure updates after insertion and swaps.

## Sample Input and Output
- **Input (input.txt)**:
  `hello world`
- **Encoded Output**:
  Binary file storing compressed data (e.g., `encoded_output.bin`).
- **Decoded Output (decoded.txt)**:
  `hello world`

## Notes and Credits
- The `BitInputStream` implementation is adapted and credited to the [Nayuki Arithmetic Coding repository](https://github.com/nayuki/Arithmetic-Coding). Proper attribution has been provided in the code comments.
- Example input and outputs are included for demonstration purposes.

## Future Improvements
1. Add support for non-ASCII encodings (e.g., Unicode).
2. Optimize tree restructuring for better performance with large datasets.
3. Extend the visualization to save the tree diagram after every step as an image.

This implementation offers a complete educational resource for understanding and using Adaptive Huffman Encoding, making it suitable for students, developers, and researchers alike
