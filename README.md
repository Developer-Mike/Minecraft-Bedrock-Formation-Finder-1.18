> [!important]
> Check out [Developer-Mike/minecraft-bedrock-generator](https://github.com/Developer-Mike/minecraft-bedrock-generator) for an updated version.

# Minecraft-Bedrock-Formation-Finder-1.18
Tool to find any bedrock formation in a 1.18 minecraft world.

## Usage
`java -jar bedrockformation.jar seed x:z type [x,y,z:bedrock]`
- seed (long)
  - Seed of your World
- x, z (int)
  - X and Z search center
- type (enum)
  - floor -> Searches on Bedrock floor
  - roof -> Searches on Bedrock roof
- Array of formation
  - x, y, z
    - Location of state
  - bedrock (enum)
    - 1 -> Bedrock wanted
    - 0 -> No Bedrock wanted

Sample:
`java -jar bedrockformation.jar 124352345 0:0 floor 0,-63,0:1 1,-62,0:1 0,-63,1:0`
